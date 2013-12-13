/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;
$(document).ready(function() {
    table = $("#table");
    table.dataTable({"bJQueryUI": true});
    table.dblclick()

    COMMON.activeMenu("quanly_nav");

    $(".cham_diem").on('keypress', function(e) {
        var keyCode = e.keyCode;
        var cell = $(this);
        if (keyCode == 13) {
            event.preventDefault();
            var idHocSinh = cell[0].parentElement.id;
            var next = cell[0].parentElement.nextSibling;
            var diemSo = parseFloat(cell[0].innerHTML);

            if (isNaN(diemSo) || (diemSo > 10 || diemSo < 0)) {
                diemSo = -1;
            }

            console.log(diemSo);

            if (next != null) {
                var cellnext = next.children[2];
                cellnext.focus();
                console.log(cellnext);
            }

            $.ajax({
                url: chamDiemURL,
                type: 'GET',
                data: {diemSo: diemSo},
                success: function() {
                    console.log("eeeeeeeeeeeeeeeeeeeeeeeedau ma thang Ly");
                }
            });

        }
    });
});


