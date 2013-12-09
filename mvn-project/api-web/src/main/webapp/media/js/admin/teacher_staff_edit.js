/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    jQuery("#form-horizontal").validationEngine();
    //update value for select 
    $("#checkBox_edit")[0].value = $("span.span_select")[0].id;
    COMMON.activeMenu("quanly_nav");
    
    var today = new Date();
    var yearToday = today.getFullYear();
    
    $( "#datepickerngaySinh" ).datepicker({
        yearRange: "1992:" + yearToday +"",
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    });
    $( "#datepickerVaoLam" ).datepicker({
        yearRange: "2000:2023",
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    });    
    $( "#datepickerNghiViec" ).datepicker({
        yearRange: "2000:2023",
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/mm/yy"
    }); 
    $('#manhanvien').focus();  
})

