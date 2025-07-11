"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function BedController($scope, $location, $http) {
    $scope.beds = [];
    $scope.specialties = [];
    $scope.newBed = {};
    $scope.filtroEspecialidade = '';
    $scope.listRooms = function () {
        $http.get('http://localhost:8080/rooms')
            .then(function (response) {
            $scope.rooms = response.data;
        });
    };
    $scope.listBeds = function () {
        $http.get('http://localhost:8080/beds')
            .then(function (response) {
            $scope.beds = response.data;
        });
    };
    $scope.listSpecialties = function () {
        $http.get('http://localhost:8080/specialties')
            .then(function (response) {
            $scope.specialties = response.data;
        });
    };
    $scope.addRoom = function () {
        if ($scope.newBed.cdRoom && $scope.newBed.cdHospital) {
            $http.post('http://localhost:8080/hwings', $scope.newBed)
                .then(function () {
                $scope.newBed = { cdRoom: '', cdHospital: '' };
                $scope.listRooms();
            });
        }
    };
    $scope.listFreeBeds = function ($specialty) {
        if ($specialty == null) {
            console.log($specialty);
            $scope.listBeds();
            return;
        }
        $http.get('http://localhost:8080/bedsSpecialty/' + $specialty)
            .then(function (response) {
            console.log($specialty);
            $scope.beds = response.data;
        });
    };
    $scope.cleaningBed = function (cdBed) {
        $http.put('http://localhost:8080/finishCleaning/' + cdBed)
            .then(function () {
            $scope.listBeds();
        });
    };
    $scope.removeBed = function (cdRoom) {
        $http.delete('http://localhost:8080/beds/' + cdRoom)
            .then(function () {
            $scope.listBeds();
            $scope.getFreeBeds();
        });
    };
    $scope.editBed = function (cdRoom) {
        $location.path('/bed/' + cdRoom + '/editar');
    };
    $scope.listLogs = function (cdBed) {
        $location.path('/bed/' + cdBed + '/log');
    };
    $scope.listBeds();
    $scope.listRooms();
    $scope.listSpecialties();
}
exports.default = BedController;
