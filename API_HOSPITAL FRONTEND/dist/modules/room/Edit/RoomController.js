"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function RoomController($scope, $http, $routeParams, $location) {
    $scope.room = null;
    $http.get('http://localhost:8080/rooms/' + $routeParams.id)
        .then(function (response) {
        $scope.room = response.data;
    });
    $scope.listStatus = function () {
        $http.get('http://localhost:8080/status')
            .then(function (response) {
            $scope.statusList = response.data;
        });
    };
    $scope.saveEdicao = function () {
        $http.put('http://localhost:8080/rooms/' + $scope.room.cdRoom, {
            deCodigo: $scope.room.deCodigo,
            cdStatus: $scope.room.cdStatus,
            cdHWing: $scope.room.cdHWing.cdHWing
        }).then(function () {
            $location.path('/rooms');
        }, function () {
            alert('Erro ao editar quarto!');
        });
    };
    $scope.voltar = function () {
        $location.path('/rooms');
    };
    $scope.listStatus();
}
exports.default = RoomController;
