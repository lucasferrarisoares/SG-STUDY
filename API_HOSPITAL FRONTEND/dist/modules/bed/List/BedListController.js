"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function BedController($scope, $location) {
    $scope.beds = [];
    $scope.newBed = { quarto: '', especialidade: '' };
    $scope.filtroEspecialidade = '';
    $scope.addBed = function () {
        if ($scope.newBed.quarto && $scope.newBed.especialidade) {
            $scope.beds.push({
                quarto: $scope.newBed.quarto,
                especialidade: $scope.newBed.especialidade
            });
            $scope.newBed = { quarto: '', especialidade: '' };
        }
    };
    $scope.verHistorico = function (bed) {
        $location.path('/bed-log/' + encodeURIComponent(bed.quarto));
    };
}
exports.default = BedController;
