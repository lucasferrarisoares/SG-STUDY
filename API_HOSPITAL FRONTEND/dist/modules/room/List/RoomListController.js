"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function RoomController($scope, $http, $location) {
    $scope.rooms = [];
    $scope.newRoom = {};
    $scope.specialties = [];
    $scope.budyRoom = 0;
    $scope.freeRoom = 0;
    $scope.totalRoom = 0;
    $scope.listRoom = function () {
        $http.get('http://localhost:8080/rooms')
            .then(function (response) {
            $scope.rooms = response.data;
        });
    };
    $scope.listSpecialties = function () {
        $http.get('http://localhost:8080/specialties')
            .then(function (response) {
            $scope.specialties = response.data;
        });
    };
    $scope.addRoom = function () {
        if ($scope.newRoom.cdSpecialty && $scope.newRoom.cdHWing && $scope.newRoom.nuBed) {
            $http.post('http://localhost:8080/rooms', $scope.newRoom)
                .then(function () {
                $scope.newRoom = { deCodigo: '', cdStatus: 0, cdSpecialty: '', cdHWing: '', nuBed: '' };
                $scope.listRoom();
            });
        }
    };
    $scope.removeRoom = function (cdRoom) {
        $http.delete('http://localhost:8080/rooms/' + cdRoom)
            .then(function () {
            $scope.listRoom();
        });
    };
    $scope.getNuRoomBySpecialty = function (cdSpecialty) {
        $http.get('http://localhost:8080/roomSpecialty/' + cdSpecialty)
            .then(function (response) {
            $scope.nuRoomDTO = response.data;
            $scope.budyRoom = $scope.nuRoomDTO.budyRoom;
            $scope.freeRoom = $scope.nuRoomDTO.freeRoom;
            $scope.totalRoom = $scope.nuRoomDTO.totalRoom;
        });
    };
    $scope.editRoom = function (cdRoom) {
        $location.path('/room/' + cdRoom + '/editar');
    };
    // $scope.getFreeRooms = function () {
    //   $http.get('http://localhost:8080/freerooms/')
    //     .then(function() {
    //       $scope.listRoom();
    //   });
    // };
    $scope.listSpecialties();
    $scope.listRoom();
}
exports.default = RoomController;
