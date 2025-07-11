"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var angular = require("angular");
var HospitalListController_1 = require("./modules/hospital/List/HospitalListController");
var HospitalController_1 = require("./modules/hospital/Edit/HospitalController");
var PatientListController_1 = require("./modules/patient/List/PatientListController");
var PatientController_1 = require("./modules/patient/Edit/PatientController");
var PatientHospitalizationController_1 = require("./modules/patient/Hopitalization/PatientHospitalizationController");
var PatientLog_1 = require("./modules/patient/Log/PatientLog");
var hWingListController_1 = require("./modules/hwing/List/hWingListController");
var hWingController_1 = require("./modules/hwing/Edit/hWingController");
var BedListController_1 = require("./modules/bed/List/BedListController");
var RoomListController_1 = require("./modules/room/List/RoomListController");
var RoomController_1 = require("./modules/room/Edit/RoomController");
var HomeController_1 = require("./home/HomeController");
var BedLogController_1 = require("./modules/bed/Log/BedLogController");
var BedController_1 = require("./modules/bed/Edit/BedController");
var HospitalizationListController_1 = require("./modules/hospitalization/List/HospitalizationListController");
var app = angular.module('meuApp', ['ngRoute']);
app.controller('HospitalController', HospitalController_1.default);
app.controller('HospitalListController', HospitalListController_1.default);
app.controller('hWingController', hWingController_1.default);
app.controller('hWingListController', hWingListController_1.default);
app.controller('PatientListController', PatientListController_1.default);
app.controller('PatientController', PatientController_1.default);
app.controller('PatientHospitalizationController', PatientHospitalizationController_1.default);
app.controller('PatientLogController', PatientLog_1.default);
app.controller('BedListController', BedListController_1.default);
app.controller('BedLogController', BedLogController_1.default);
app.controller('BedController', BedController_1.default);
app.controller('RoomListController', RoomListController_1.default);
app.controller('RoomController', RoomController_1.default);
app.controller('HomeController', HomeController_1.default);
app.controller('HospitalizationListController', HospitalizationListController_1.default);
app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/home', {
            templateUrl: 'src/home/Home.html',
            controller: 'HomeController'
        })
            .when('/hospital', {
            templateUrl: 'src/modules/hospital/List/HospitalList.html',
            controller: 'HospitalListController'
        })
            .when('/hospital/:id/editar', {
            templateUrl: 'src/modules/hospital/Edit/Hospital.html',
            controller: 'HospitalController'
        })
            .when('/patient', {
            templateUrl: 'src/modules/patient/List/PatientList.html',
            controller: 'PatientListController'
        })
            .when('/patient/:id/editar', {
            templateUrl: 'src/modules/patient/Edit/Patient.html',
            controller: 'PatientController'
        })
            .when('/patient/:id/internar', {
            templateUrl: 'src/modules/patient/Hopitalization/PatientHospitalization.html',
            controller: 'PatientHospitalizationController'
        })
            .when('/patientlogs/:id', {
            templateUrl: 'src/modules/patient/Log/PatientLog.html',
            controller: 'PatientLogController'
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
            templateUrl: 'src/modules/bed/List/BedList.html',
            controller: 'BedListController'
        })
            .when('/bed/:id/editar', {
            templateUrl: 'src/modules/bed/Edit/Bed.html',
            controller: 'BedListController'
        })
            .when('/bed/:id/log', {
            templateUrl: 'src/modules/bed/Log/BedLog.html',
            controller: 'BedLogController'
        })
            .when('/room', {
            templateUrl: 'src/modules/room/List/RoomList.html',
            controller: 'RoomListController'
        })
            .when('/room/:id/editar', {
            templateUrl: 'src/modules/room/Edit/Room.html',
            controller: 'RoomController'
        })
            .when('/hospitalization', {
            templateUrl: 'src/modules/hospitalization/List/HospitalizationList.html',
            controller: 'HospitalizationListController'
        })
            .otherwise({
            redirectTo: '/home'
        });
    }
]);
