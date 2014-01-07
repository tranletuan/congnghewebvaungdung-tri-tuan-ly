/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var urlOnlineStatus = /*[[@{/guest/check_online}]]*/"check_online";
var listUser = /*[[${listUser}]]*/"listUsers";

$(document).ready(function() {

    var rf = setInterval(receiveStatus, 3000);

});


function receiveStatus() {
    $.ajax({
        url: urlOnlineStatus,
        type: 'GET',
        data: {listUser: listUser},
        success: function(data) {
            $("#list_online").html(data);
        },
        error: function(data) {
            console.log(data);
            console.log("Error");
        }
    });
}




















//function init() {
//    var amq = org.activemq.Amq;
//    amq.init({
//        uri: 'amq',
//        logging: true,
//        timeout: 20
//    });
//
//
//    var myHandler =
//            {
//                rcvMessage: function(message)
//                {
//                    document.writeln(message);
//                }
//            };
//
//
//    amq.addListener("test", myDestination, myHandler.rcvMessage);
//
//}
