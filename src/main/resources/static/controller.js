var appMain = angular.module('app', ['ui.bootstrap']);

appMain.controller("MainController", function($scope, $q, $http, $timeout){

    var main = this;

    main.data = ["eeeeeee"];

    main.load2 = function () {
        main.data = ["eeeeeee", main.data];
    };

    main.load = function () {

// https://webukol.herokuapp.com/greeting
// '/greeting'
        $http.get('/greeting').
        success(function(data, status, headers, config) {

            console.log(" /greeting #### : "+data);

            main.data = data;

        }).
        error(function(data, status, headers, config) {

            console.log("/info ####  error= "+error);

        });


    };

    main.players = []
    main.spinner = false;


    main.loadPlayers = function () {
        main.spinner = true;

        $http.get('/players').
        success(function(data, status, headers, config) {
            console.log(" /greeting #### : "+data);

            main.players = data;
            main.spinner = false;
        }).
        error(function(data, status, headers, config) {
                console.log("/info ####  error= "+error);
                main.spinner = false;
        });
    };


});

