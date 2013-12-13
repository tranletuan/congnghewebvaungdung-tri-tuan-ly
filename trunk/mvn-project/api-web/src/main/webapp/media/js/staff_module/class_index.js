/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function loadLopHoc(namHocId){
    $.ajax({
        url: load_lophoc_url,
        type: 'GET',
        data: {namHocId: namHocId},
        success: function(data){
            $("#mainTable").html(data);
        }
    })
}
$(document).ready(function(){
    var namHocId = $(".namHocHienTai")[0].id;
    loadLopHoc(namHocId);
    $("#IDNamHoc").on('change', function(){
        var self = $(this);        
        namHocId = self[0].value;        
        loadLopHoc(namHocId);
        
    })
})


