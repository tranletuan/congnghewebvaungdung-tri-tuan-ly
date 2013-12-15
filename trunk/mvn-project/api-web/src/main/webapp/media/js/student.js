/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var table;
var link_download;
$(document).ready(function(){    
    //set nam Hoc hien tai cho select
    var namHocHienTai = $("span.namHocHienTai");
    if(namHocHienTai.length > 0){
        $("#IDNamHoc")[0].value = namHocHienTai[0].id;
    }
    var khoiLopHienTai = $("span.khoiLopMacDinh");
    if (khoiLopHienTai.length > 0) {
        $("#IDKhoiLop")[0].value = khoiLopHienTai[0].id; 
    }           
    //send Ajax Request to get data when loading
    var namHocId = $("#IDNamHoc")[0].value;
    var khoiLopId = $("#IDKhoiLop")[0].value;  
    $.ajax({
        url: load_url,
        type: 'GET',
        data: {namHocId: namHocId, khoiLopId: khoiLopId},
        success: function(data){
            $("#mainContent").html(data);
        }
    })
    
    //set cac thanh phan khi co su kien change select
    $("#IDNamHoc").on('change', function(){
        //Goi len controller 
        var self = $(this);              
        var namHocIdLocal = self[0].value;
        var khoiLopIdLocal = $("#IDKhoiLop")[0].value;
        $.ajax({
            url: request_data_url,
            type: 'GET',
            data: { namHocId: namHocIdLocal, khoiLopId: khoiLopIdLocal, requestElement: "namHoc"},
            success: function(data){                         
                $("#mainContent").html(data);
            }            
        })
        
    })
    $("#IDKhoiLop").on('change', function(){
        var self = $(this);
        var khoiLopIdLocal = self[0].value;
        var namHocIdLocal = $("#IDNamHoc")[0].value;        
        $.ajax({
            url: request_data_url,
            type: 'GET',
            data: { namHocId: namHocIdLocal, khoiLopId: khoiLopIdLocal, requestElement: "khoiLop"},
            success: function(data){                              
                $("#mainContent").html(data);
            }            
        })
    })    

    $("#xepLopButton").on('click', function(){        
        var url = xeplop_url;
        var chonKhoiSau = $("#radios-0")[0].checked;
        var dataChon = "6789";
        if (chonKhoiSau) {
            dataChon = "6";           
        }
         $.ajax({
                url: url,
                type: 'POST',
                data: {radio_value: dataChon},
                success: function(){
                    document.location = index_url;
                }
        });
        //Close Modal
        $('#xepLopHocSinhModal').modal('hide');        
    });
    
    $("#btnhapDanhSachHocSinh").on('click', function(){        
        $("#formNhapDanhSach").submit();
    })
    
    $("#btxuatDanhSachHocSinh").on('click', function(){
        var valueExport = $("#radiosXuatDS-0")[0].checked;
        var exportType = "0";// mac dinh la export theo danh sacsh lpp
        if (valueExport) {
            exportType = "1"; //export theo danh sach hocsinh
        }        
        $.ajax({
            url: xuatDanhSachHS_url,
            type: 'POST',
            data: {exportType: exportType},
            success: function(res){                                            
                $('#taiFileExport').modal('show');              
            }
        })
        $('#xuatDanhSachHocSinh').modal('hide');              
    })
    
    $("#linkTaiFileExport").on('click', function(){
        $('#taiFileExport').modal('hide');    
    })
}) 
