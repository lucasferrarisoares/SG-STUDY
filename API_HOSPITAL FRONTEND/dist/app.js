"use strict";
exports.__esModule = true;
/// <reference types="angular-route" />
var angular = require("angular");
require("angular-route");
var app = angular.module('meuApp', ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/home', {
            templateUrl: 'src/home/home.html',
            controller: 'MeuController'
        })
            .otherwise({
            redirectTo: '/home'
        });
    }]);
