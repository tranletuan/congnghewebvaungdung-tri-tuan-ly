/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * jquery.charcounter.js version 1.2
 * requires jQuery version 1.2 or higher
 * Copyright (c) 2007 Tom Deater (http://www.tomdeater.com)
 * Licensed under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 * 
 */
function loadNews(obj) {
    var catalogId = obj===null? $("#catalogs").children(".active")[0].id : obj.id;
    catalogId = catalogId==="" ? 0:catalogId;
    console.log(catalogId);
    var page  = $("#page_num").children(".active")[0].children[0].text;
    jQuery.ajax({
        url: newsUrl + "/" +catalogId + "/" + page,
        success: function(data) {
            jQuery('#list_news').html(data);
        }
    });
}
;
function loadFrame() {
    jQuery.ajax({
        url: viewUrl,
        success: function(data) {
            jQuery('#login-frame').html(data);
        }
    });
}
;
function loadCarousel() {
    var carousel = $(this).find('.carousel').hide();
    var deferreds = [];
    var imgs = $('.carousel', this).find('img');
    // loop over each img
    imgs.each(function() {
        var self = $(this);
        var datasrc = self.attr('data-src');
        if (datasrc) {
            var d = $.Deferred();
            self.one('load', d.resolve)
                    .attr("src", datasrc)
                    .attr('data-src', '');
            deferreds.push(d.promise());
        }
    });

    $.when.apply($, deferreds).done(function() {
        $('#ajaxloader').hide();
        carousel.fadeIn(1000);
    });
}
;
(function($) {
    /**
     * attaches a character counter to each textarea element in the jQuery object
     * usage: $("#myTextArea").charCounter(max, settings);
     */

    $.fn.charCounter = function(max, settings) {
        max = max || 100;
        settings = $.extend({
            container: "<span></span>",
            classname: "charcounter",
            format: "(%1 kí tự còn lại)",
            pulse: true,
            delay: 0
        }, settings);
        var p, timeout;

        function count(el, container) {
            el = $(el);
            if (el.val().length > max) {
                el.val(el.val().substring(0, max));
                if (settings.pulse && !p) {
                    pulse(container, true);
                }
                ;
            }
            ;
            if (settings.delay > 0) {
                if (timeout) {
                    window.clearTimeout(timeout);
                }
                timeout = window.setTimeout(function() {
                    container.html(settings.format.replace(/%1/, (max - el.val().length)));
                }, settings.delay);
            } else {
                container.html(settings.format.replace(/%1/, (max - el.val().length)));
            }
        }
        ;

        function pulse(el, again) {
            if (p) {
                window.clearTimeout(p);
                p = null;
            }
            ;
            el.animate({opacity: 0.1}, 100, function() {
                $(this).animate({opacity: 1.0}, 100);
            });
            if (again) {
                p = window.setTimeout(function() {
                    pulse(el);
                }, 200);
            }
            ;
        }
        ;

        return this.each(function() {
            var container;
            if (!settings.container.match(/^<.+>$/)) {
                // use existing element to hold counter message
                container = $(settings.container);
            } else {
                // append element to hold counter message (clean up old element first)
                $(this).next("." + settings.classname).remove();
                container = $(settings.container)
                        .insertAfter(this)
                        .addClass(settings.classname);
            }
            $(this)
                    .unbind(".charCounter")
                    .bind("keydown.charCounter", function() {
                        count(this, container);
                    })
                    .bind("keypress.charCounter", function() {
                        count(this, container);
                    })
                    .bind("keyup.charCounter", function() {
                        count(this, container);
                    })
                    .bind("focus.charCounter", function() {
                        count(this, container);
                    })
                    .bind("mouseover.charCounter", function() {
                        count(this, container);
                    })
                    .bind("mouseout.charCounter", function() {
                        count(this, container);
                    })
                    .bind("paste.charCounter", function() {
                        var me = this;
                        setTimeout(function() {
                            count(me, container);
                        }, 10);
                    });
            if (this.addEventListener) {
                this.addEventListener('input', function() {
                    count(this, container);
                }, false);
            }
            ;
            count(this, container);
        });
    };

})(jQuery);

$(function() {
    $(".counted").charCounter(320, {container: "#counter"});
});



