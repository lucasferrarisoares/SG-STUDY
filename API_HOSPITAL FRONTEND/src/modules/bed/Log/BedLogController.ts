export default function BedLogController($scope: any, $routeParams: any, $http: any, $location: any) {
  $scope.beds = [];

    $http.get(`http://localhost:8080/bedsHistory/` + $routeParams.id)
        .then(function(response: any) {
        $scope.beds = response.data;
    });

    $scope.voltar = function() {
        $location.path('/bed');
    };
}