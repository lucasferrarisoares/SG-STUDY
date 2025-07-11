import * as angular from 'angular';


import HospitalListController from './modules/hospital/List/HospitalListController';
import HospitalController from './modules/hospital/Edit/HospitalController';
import PatientListController from './modules/patient/List/PatientListController';
import PatientController from './modules/patient/Edit/PatientController';
import PatientHospitalizationController from './modules/patient/Hopitalization/PatientHospitalizationController';
import PatientLogController from './modules/patient/Log/PatientLog';
import hWingListController from './modules/hwing/List/hWingListController';
import hWingController from './modules/hwing/Edit/hWingController';
import BedListController from './modules/bed/List/BedListController';
import RoomListController from './modules/room/List/RoomListController';
import RoomController from './modules/room/Edit/RoomController';
import HomeController from './home/HomeController';
import BedLogController from './modules/bed/Log/BedLogController';
import BedController from './modules/bed/Edit/BedController';
import HospitalizationListController from './modules/hospitalization/List/HospitalizationListController';

const app = angular.module('meuApp', ['ngRoute']);

app.controller('HospitalController', HospitalController);
app.controller('HospitalListController', HospitalListController);
app.controller('hWingController', hWingController); 
app.controller('hWingListController', hWingListController);
app.controller('PatientListController', PatientListController);
app.controller('PatientController', PatientController);
app.controller('PatientHospitalizationController', PatientHospitalizationController);
app.controller('PatientLogController', PatientLogController); 
app.controller('BedListController', BedListController);
app.controller('BedLogController', BedLogController);
app.controller('BedController', BedController); 
app.controller('RoomListController', RoomListController);
app.controller('RoomController', RoomController);
app.controller('HomeController', HomeController);
app.controller('HospitalizationListController', HospitalizationListController);

app.config(['$routeProvider', function($routeProvider: ng.route.IRouteProvider) {
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