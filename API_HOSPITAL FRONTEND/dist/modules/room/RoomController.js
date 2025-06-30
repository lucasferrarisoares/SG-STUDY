"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function RoomController($scope) {
    $scope.rooms = [];
    $scope.newRoom = { ala: '', leitos: 1, ocupados: 0, especialidade: '' };
    $scope.addRoom = function () {
        if ($scope.newRoom.ala && $scope.newRoom.leitos > 0 && $scope.newRoom.especialidade) {
            $scope.rooms.push({
                ala: $scope.newRoom.ala,
                leitos: $scope.newRoom.leitos,
                ocupados: 0,
                especialidade: $scope.newRoom.especialidade
            });
            $scope.newRoom = { ala: '', leitos: 1, ocupados: 0, especialidade: '' };
        }
    };
    $scope.ocuparLeito = function (room) {
        if (room.ocupados < room.leitos) {
            room.ocupados++;
        }
    };
    $scope.liberarLeito = function (room) {
        if (room.ocupados > 0) {
            room.ocupados--;
        }
    };
    $scope.getFreeRooms = function () {
        return $scope.rooms.filter(function (r) { return r.leitos > r.ocupados; });
    };
    $scope.getOccupiedRooms = function () {
        return $scope.rooms.filter(function (r) { return r.ocupados > 0; });
    };
    $scope.occupiedBySpecialty = {};
    $scope.$watch('rooms', function (rooms) {
        var result = {};
        rooms.forEach(function (r) {
            if (r.ocupados > 0) {
                result[r.especialidade] = (result[r.especialidade] || 0) + 1;
            }
        });
        $scope.occupiedBySpecialty = result;
    }, true);
}
exports.default = RoomController;
