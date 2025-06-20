import * as angular from 'angular';


import HospitalListController from './modules/hospital/List/HospitalListController';
import HospitalController from './modules/hospital/Edit/HospitalController';
import PatientController from './modules/patient/PatientController';
import hWingListController from './modules/hwing/List/hWingListController';
import hWingController from './modules/hwing/Edit/hWingController';
import BedController from './modules/bed/BedController';
import RoomController from './modules/room/RoomController';
import HomeController from './home/HomeController';

const app = angular.module('meuApp', ['ngRoute']);

app.controller('HospitalController', HospitalController);
app.controller('PatientController', PatientController);
app.controller('hWingListController', hWingListController);
app.controller('hWingController', hWingController); 
app.controller('BedController', BedController);
app.controller('RoomController', RoomController);
app.controller('HomeController', HomeController);
app.controller('HospitalListController', HospitalListController);

app.config(['$routeProvider', function($routeProvider: ng.route.IRouteProvider) {
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

