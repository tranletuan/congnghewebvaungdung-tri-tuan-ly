/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function confirm(message, id, callback) {
	$(id).modal({
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				modal.close(); // or $.modal.close();
			});
		}
	});
}
var table;
$(document).ready(function(){
    var self;       
    table = $("#table");
    table.dataTable({"bJQueryUI": true});
    COMMON.activeMenu("quanly_nav");
    table.on('click','.linkDelete', function(e){                            
        e.preventDefault();
        self = $(this);  
        var message = "Are you sure you want to delete this student?";
        var url = "/api-web/staff/management/students/delete/" + self[0].id;        
        // example of calling the confirm function
        // you must use a callback function to perform the "yes" action
        id = '#confirm';
        confirm(message, id, function () {
            console.log("ABC");
            console.log(url);
            
             $.ajax({
                url: url,
                type: 'POST'
            });
            document.location = "/api-web/staff/management/students/index"
            
        });
    });    
    
    $('#xepLopHocSinh').on('click', function(e){
        e.preventDefault();
        self = $(this);
        var url = "/api-web/staff/management/students/xeplop";
        id = '#confirm';
        message = "Bạn có chắc là bạn muốn xếp lớp cho học sinh mới hay không?";
        confirm(message, id, function(){
           $.ajax({
                url: url,
                type: 'POST'
           });
           document.location = "/api-web/staff/management/students/index";
        });
    })
}) 
