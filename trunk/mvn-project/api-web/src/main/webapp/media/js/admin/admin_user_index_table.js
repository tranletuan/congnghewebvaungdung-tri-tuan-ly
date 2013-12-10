/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*<![CDATA[*/
$("#table").on('click',".enabled", function() {
    var self = $(this);
    var id = self.parent().parent().attr("id");
    var nonlocked = $("#" + id)[0].children[2].children[0].checked;
    var enabled = self[0].checked;
    senddata(id, nonlocked, enabled);
});
$("#table").on('click', ".nonlocked", function() {
       var self = $(this);
    var id = self.parent().parent().attr("id");
    var enabled = $("#" + id)[0].children[1].children[0].checked;
    var nonlocked = self[0].checked;
    senddata(id, nonlocked, enabled);
});
function senddata(id, nonlocked, enabled) {
  var en = enabled?1:0;
  var non = nonlocked?1:0;
    jQuery.ajax({
        url: suburl + id + "/" + en + "/" + non,
        success: function (data) {}
    });
};
/*]]>*/