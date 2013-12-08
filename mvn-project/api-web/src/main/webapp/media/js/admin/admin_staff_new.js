/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    var today = new Date();
    var yearToday = today.getFullYear();
    $( "#datepickerngaySinh" ).datepicker({
        yearRange: "1992:" + yearToday +"",
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    });
    $( "#datepickerngayVaoLam" ).datepicker({
        yearRange: "2000:2023",
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    });
    $('#manhanvien').focus();
});
