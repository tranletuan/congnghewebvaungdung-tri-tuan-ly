/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*<![CDATA[*/
function loadframe() {
    jQuery.ajax({
        url: viewUrl + $("#rolename").val(),
        success: function(data) {
            jQuery('#tableframe').html(data);
        }
    });
}
;
/*]]>*/