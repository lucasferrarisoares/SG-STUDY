"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function RoomController($scope, $http, $location) {
    $scope.rooms = [];
    $scope.freeRooms = [];
    $scope.occupiedRooms = [];
    $scope.newRoom = {};
    $scope.specialties = [];
    $scope.statusList = [];
    $scope.specialty = null;
    $scope.nuRoomDTO = {
        budyRoom: "",
        freeRoom: "",
        totalRoom: ""
    };
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
    $scope.listStatus = function () {
        $http.get('http://localhost:8080/status')
            .then(function (response) {
            $scope.statusList = response.data;
        });
    };
    $scope.addRoom = function () {
        if ($scope.newRoom.deCodigo && $scope.newRoom.cdStatus && $scope.newRoom.cdHWing && $scope.newRoom.nuBed) {
            $http.post('http://localhost:8080/rooms', $scope.newRoom)
                .then(function () {
                $scope.newRoom = {};
                $scope.listRoom();
                $scope.getFreeRooms();
            });
        }
    };
    $scope.removeRoom = function (cdRoom) {
        $http.delete('http://localhost:8080/rooms/' + cdRoom)
            .then(function () {
            $scope.listRoom();
            $scope.getFreeRooms();
        });
    };
    $scope.editRoom = function (cdRoom) {
        $location.path('/room/' + cdRoom + '/editar');
    };
    $scope.getNuRoomBySpecialty = function (specialty) {
        $http.get('http://localhost:8080/roomSpecialty/' + specialty)
            .then(function (response) {
            $scope.nuRoomDTO = response.data;
            $scope.nuRoomDTO.budyRoom = $scope.nuRoomDTO.budyRoom;
            $scope.nuRoomDTO.freeRoom = $scope.nuRoomDTO.freeRoom;
            $scope.nuRoomDTO.totalRoom = $scope.nuRoomDTO.totalRoom;
        });
    };
    // Busca quartos livres do endpoint correto
    $scope.listFreeRooms = function () {
        $http.get('http://localhost:8080/freerooms')
            .then(function (response) {
            $scope.freeRooms = response.data;
        });
    };
    $scope.listSpecialties();
    $scope.listStatus();
    $scope.listFreeRooms();
}
exports.default = RoomController;
