/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var table;
$(document).ready(function(){    
    //set nam Hoc hien tai cho select
    $("#IDNamHoc")[0].value = $("span.namHocHienTai")[0].id;
    $("#IDKhoiLop")[0].value = $("span.khoiLopMacDinh")[0].id;    
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
                console.log("Success");                
                $("#mainContent").html(data);
            }            
        })

        console.log("Nam Hoc Change");
    })
    $("#IDKhoiLop").on('change', function(){
        var self = $(this);
        var khoiLopIdLocal = self[0].value;
        var namHocIdLocal = $("#IDNamHoc")[0].value;
        console.log("Khoi Lop Change");
        $.ajax({
            url: request_data_url,
            type: 'GET',
            data: { namHocId: namHocIdLocal, khoiLopId: khoiLopIdLocal, requestElement: "khoiLop"},
            success: function(data){
                console.log("Success");                
                $("#mainContent").html(data);
            }            
        })
    })
    //Chi thay doi danh sach hoc sinh khi co thong tin lop hoc thay doi
    
//    table.on('click','.linkDelete', function(e){                            
//        e.preventDefault();
//        self = $(this);  
//        var message = "Are you sure you want to delete this student?";
//        var url = "/api-web/staff/management/students/delete/" + self[0].id;        
//        // example of calling the confirm function
//        // you must use a callback function to perform the "yes" action
//        id = '#confirm';
//        confirm(message, id, function () {
//            console.log("ABC");
//            console.log(url);
//            
//             $.ajax({
//                url: url,
//                type: 'POST'
//            });
//            document.location = request_data_url;
//            
//        });
//    });    

    $("#xepLopButton").on('click', function(){        
        var url = xeplop_url;
         $.ajax({
                url: url,
                type: 'POST',
                success: function(){
                    document.location = index_url;
                }
        });
        //Close Modal
        $('#xepLopHocSinhModal').modal('hide');        
    })
}) 
