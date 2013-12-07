/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
$(document).ready(function(){
    table = $("#table");
    table.dataTable({"bJQueryUI": true});
    COMMOM.activeMenu("quanly_nav");
})

