/**
 * Created by 437-5 on 25.10.2018.
 */
$(document).ready(function () {

    var first_btn = $("#get_all");
    var second_btn = $("#add_post");
    first_btn.on('click', function () {

        $.ajax({
            type: "GET",
            url: "http://localhost:8080//testTask/api/logIn",
            dataType: 'json',
            contentType: "application/json; charset=utf-8"

        }).fail(function (response) {
            window.location.href = "https://oauth.vk.com/authorize?client_id=6731226&display=page&redirect_uri=http://localhost:8080/testTask/api/logIn&scope=wall&response_type=code&v=5.87";

        })/*.always(function (response) {
         console.log(response);
         var x = response.responseText;
         console.log(x);

         })*/;
        // data: JSON.stringify(x)
    });

    second_btn.on('click', function () {
        var x = {
            post: "true"
        };
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/testTask/api/logIn",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: x
        }).done(function (response) {

        }).fail(function (response) {
            window.location.href = "https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/logIn&scope=wall&response_type=code&v=5.87";

        })
    });
    /*.done(function (response) {
     console.log(response);
     //console.log(JSON.parse(response));
     //console.log("Добавилось. Всё ОК");
     //console.log(response);

     }).fail(function (response) {
     console.log(response);
     })*/


    /*btn.on('click', function () {
     $.ajax({
     type: "GET"
     //url: "https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/getToken&scope=wall&response_type=code&v=5.87"
     }).always(function (response) {
     window.location.href = "https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/getToken&scope=wall&response_type=code&v=5.87"
     //response.get(code);
     $.ajax({
     type: "GET"
     }).always(function (response) {
     //window.location.href = "https://oauth.vk.com/access_token?client_id=6731226&client_secret=hLu7TXcJ7PKQ3vwvWoNI&redirect_uri=http://localhost:8080/testTask/api/getToken2&code= +
     })
     });

     });*/
    //var btn = $("#begin");

    /* btn.on('click', function (response) {
     window.location.href = "https://oauth.vk.com/authorize?client_id=6731226&display=popup&redirect_uri=http://localhost:8080/testTask/api/getToken&scope=wall&response_type=code&v=5.87"
     console.log(response);

     });*/
});

function User(login) {
    this.login = login

}
