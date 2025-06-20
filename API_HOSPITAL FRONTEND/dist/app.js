"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var angular = require("angular");
var HospitalListController_1 = require("./modules/hospital/List/HospitalListController");
var HospitalController_1 = require("./modules/hospital/Edit/HospitalController");
var PatientController_1 = require("./modules/patient/PatientController");
var hWingListController_1 = require("./modules/hwing/List/hWingListController");
var hWingController_1 = require("./modules/hwing/Edit/hWingController");
var BedController_1 = require("./modules/bed/BedController");
var RoomController_1 = require("./modules/room/RoomController");
var HomeController_1 = require("./home/HomeController");
var app = angular.module('meuApp', ['ngRoute']);
app.controller('HospitalController', HospitalController_1.default);
app.controller('PatientController', PatientController_1.default);
app.controller('hWingListController', hWingListController_1.default);
app.controller('hWingController', hWingController_1.default);
app.controller('BedController', BedController_1.default);
app.controller('RoomController', RoomController_1.default);
app.controller('HomeController', HomeController_1.default);
app.controller('HospitalListController', HospitalListController_1.default);
app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/hospital', {
            templateUrl: 'src/modules/hospital/List/HospitalList.html',
            controller: 'HospitalListController'
        })
            .when('/hospitais/:id/editar', {
            templateUrl: 'src/modules/hospital/Edit/Hospital.html',
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
            templateUrl: 'src/modules/hwing/List/hWingList.html',
            controller: 'hWingListController'
        })
            .when('/hwing/:id/editar', {
            templateUrl: 'src/modules/hwing/Edit/hWing.html',
            controller: 'hWingController'
        })
            .when('/bed', {
            templateUrl: 'src/modules/bed/Bed.html',
            controller: 'BedController'
        })
            .when('/room', {
            templateUrl: 'src/modules/room/Room.html',
            controller: 'RoomController'
        })
            .otherwise({
            redirectTo: '/home'
        });
    }
]);
