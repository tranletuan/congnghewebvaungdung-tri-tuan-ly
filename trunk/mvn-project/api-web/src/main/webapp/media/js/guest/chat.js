/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var chat_tab;

$(document).ready(function() {

    var rf = setInterval(receiveMessageChat, 1000);

});
var chat_content;
function receiveMessageChat() {
    chat_tab = $("#chat_tab")[0];
    for (var i = 0; i < chat_tab.children.length; i++) {
        var chat_element = chat_tab.children[i];
        chat_content = chat_element.children[1];
        var sendId = chat_element = chat_tab.children[i].id;
        var receiveId = userId;
        receiveMessage(sendId, receiveId);
    }
}
var content;
function receiveMessage(sendId, receiveId) {

    $.ajax({
        url: "receiveChat",
        type: 'GET',
        data: {sendId: sendId, receiveId: receiveId},
        success: function(data) {
            if (data !== null && data !== "") {
                content = $(data);
                if (content[1] != null) {
                    setTimeout(function() {
                        console.log(content.length);
                        for (var i = 1; i < content.length - 1; i++) {
                            chat_content.appendChild(content[i]);
                        }
                    }, 1000);

                }

            }

        },
        error: function(data) {
//            console.log(data);
            console.log("Error");
        }
    });
}

function sendMessage(sendId, receiveId, message) {
    $.ajax({
        url: "sendMessage",
        type: 'GET',
        data: {sendId: sendId, receiveId: receiveId, message: message},
        success: function(data) {

        },
        error: function(data) {
            console.log(data);
            console.log("Error");
        }
    });
}