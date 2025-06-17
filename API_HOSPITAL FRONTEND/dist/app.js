"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var angular = require("angular");
var HospitalController_1 = require("./modules/hospital/HospitalController");
var PatientController_1 = require("./modules/patient/PatientController");
var hWingController_1 = require("./modules/hwing/hWingController");
// import BedController from './modules/bed/BedController';
var HomeController_1 = require("./home/HomeController");
var app = angular.module('meuApp', ['ngRoute']);
app.controller('HospitalController', HospitalController_1.default);
app.controller('PatientController', PatientController_1.default);
app.controller('hWingController', hWingController_1.default);
// app.controller('BedController', BedController);
app.controller('HomeController', HomeController_1.default);
app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/hospital', {
            templateUrl: 'src/modules/hospital/Hospital.html',
            controller: 'HospitalController'
        })
            .when('/home', {
            templateUrl: 'src/home/Home.html',
            controller: 'HomeController'
        })
            .when('/patient', {
            templateUrl: 'src/modules/patient/Patient.html',
            controller: 'PatientController'
        })
            .when('/hwing', {
            templateUrl: 'src/modules/hwing/hWing.html',
            controller: 'hWingController'
        })
            .when('/bed', {
            templateUrl: 'src/modules/bed/Bed.html',
            controller: 'BedController'
        })
            .otherwise({
            redirectTo: '/home'
        });
    }
]);
