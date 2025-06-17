import * as angular from 'angular';


import HospitalController from './modules/hospital/HospitalController';
import PatientController from './modules/patient/PatientController';
import HwingController from './modules/hwing/hWingController';
// import BedController from './modules/bed/BedController';
import HomeController from './home/HomeController';

const app = angular.module('meuApp', ['ngRoute']);

app.controller('HospitalController', HospitalController);
app.controller('PatientController', PatientController);
app.controller('hWingController', HwingController);
// app.controller('BedController', BedController);
app.controller('HomeController', HomeController);

app.config(['$routeProvider', function($routeProvider: ng.route.IRouteProvider) {
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

