/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
var test;
var diemCu = -1;
var diemSo;
var idDiem;
$(document).ready(function() {
    table = $("#table");
    table.dataTable({"bJQueryUI": true});
    table.dblclick()

    COMMON.activeMenu("quanly_nav");

    $(".cham_diem").on('focus', function() {
        var cell = $(this);
        var diemCu = cell[0].value;
        if (isNaN(diemCu)) {
            diemCu = -1;
        }
    });

    $(".cham_diem").on('mouseup', function( ) {
        $(this).select();
    });

    $(".cham_diem").on('keypress', function(e) {
        var keyCode = e.keyCode;
        var cell = $(this);
        if (keyCode == 13) {

            event.preventDefault();
            idDiem = cell[0].parentElement.parentElement.id;
            diemSo = parseFloat(cell[0].value);
            var next = cell[0].parentElement.parentElement.nextSibling;
            cell[0].value = diemSo;

            if (isNaN(diemSo) || (diemSo > 10 || diemSo < 0)) {
                diemSo = -1;
                cell[0].value = "chưa có";
                cell[0].select();
            }
            else {
                if (next != null) {
                    var cellnext = next.children[2].children;
                    cellnext[0].select();
                }

                //goi ajax khi thuc hien dung
                $.ajax({
                    url: chamDiemURL,
                    type: 'GET',
                    data: {diemCu: diemCu, diemSo: diemSo, idDiem: idDiem, loaiDiemKT : loaiDiemKT},                    
                    success: function() {
                        
                    }                  
                });
            }
        }
    });
});
//
//function selectText(obj) {
//    if (document.selection) {
//        var range = document.body.createTextRange();
//        range.moveToElementText(obj);
//        range.select();
//    }
//    console.log(document.createRange());
////    else if (window.getSelection) {
////        var range = document.createRange();
////        range.selectNode(obj.firstChild);
////        window.getSelection().addRange(range);
////    }
//}




