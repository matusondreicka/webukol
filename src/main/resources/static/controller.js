var appMain = angular.module('app', ['ui.bootstrap']);

appMain.controller("MainController", function($scope, $q, $http, $timeout){

    var main = this;

    main.players = []
    main.waiting = false;

    main.loadPlayers = function () {
        main.waiting = true;

        $http.get('/players').
        success(function(data, status, headers, config) {
            main.players = data;
            main.waiting = false;
        }).
        error(function(data, status, headers, config) {
                console.log("/players Error: "+error);
                main.waiting = false;
        });
    };

});

