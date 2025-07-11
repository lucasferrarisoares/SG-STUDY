export default function PatientLogController($scope: any, $http: any, $routeParams: any, $location: any) {
    $scope.patient = null;
    $scope.logs = [];
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.hasNextPage = false;

    // Buscar paciente
    $http.get('http://localhost:8080/patients/' + $routeParams.id)
        .then(function(response: any) {
        $scope.patient = response.data;
    });


    $scope.listLogs = function () {
    $http.get(`http://localhost:8080/historicHospitalization/${$routeParams.id}?page=${$scope.currentPage}&size=${$scope.pageSize}`)
        .then(function(response) {
        $scope.logs = response.data.content;
        $scope.hasNextPage = !response.data.last;
        });
    };

    $scope.nextPage = function () {
    if ($scope.hasNextPage) {
        $scope.currentPage++;
        $scope.listLogs();
    }
    };

    $scope.previousPage = function () {
    if ($scope.currentPage > 0) {
        $scope.currentPage--;
        $scope.listLogs();
    }
    };

    $scope.voltar = function() {
        $location.path('/patients/' + $routeParams.id + '/internar');
    };

    $scope.listLogs();
}