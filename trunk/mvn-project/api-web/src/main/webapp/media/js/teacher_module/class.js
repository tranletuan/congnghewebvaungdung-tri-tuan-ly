/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
var target;
var url;
$(document).ready(function() {
    table = $("#table");
    table.dataTable({"bJQueryUI": true});
    COMMON.activeMenu("quanly_nav");

//    $(".icon-action").on('click', function(e) {
//        event.preventDefault();
//        target = $(this);
//        var loaiDiem = target[0].parentElement.id;
//        var idPhanCong = target[0].parentElement.parentElement.parentElement.id;
//
//        $.ajax({
//            url: chamDiemURL,
//            type: 'GET',
//            data: {loaiDiem: loaiDiem, idPhanCong: idPhanCong},
//            success: function() {
//                console.log("eeeeeeeeeeeeeeeeeeeeeeeedau ma thang Ly");
//            }
//        });
//    });


});