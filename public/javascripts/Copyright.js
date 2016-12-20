app.controller('Copyright', ['$scope', '$mdSidenav', 'ShamayimFunctions', '$state','$http','$rootScope', function ($scope, $mdSidenav, ShamayimFunctions, $state,$http,$rootScope) {

    
    $scope.userName = ShamayimFunctions.getCookie("username");
    $scope.isManager = function()
    {
        if(ShamayimFunctions.getPermissionCookie() == "0")
            {
            return true;
        }else{
         return false;   
        }
    }
      
    // Language Section
    $scope.dictionary;

    $scope.Languages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            HouseLanguage: 'default'
        }
    };

    $scope.Languages = ShamayimFunctions.getExistingLanguages();

    function getLanguage(szLanguageName) {
        // Get information conserning the house
        $http.get("/GET_LANGUAGE/" + szLanguageName)
            .then(function successCallback(response) {
                    $scope.dictionary = response.data;
                    $rootScope.pageDirection = $scope.dictionary.Dictionary[0].PageDirection;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        ShamayimFunctions.setLanguageCookie(szLanguageName);

    }

    var languageToGet = ShamayimFunctions.setLanguageCookie();

    if(languageToGet == null)
    {
    languageToGet = "עברית";
    }

    getLanguage(languageToGet);

    $scope.$watch('Languages.selectedOption', function (newVal, oldVal) {
            if (newVal != oldVal) {
                HouseLanguageName = newVal;
                getLanguage(newVal);

            }
        })

     // End Of Language Section
    
    $scope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $scope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.goToHouses = function () {
        $state.go('Houses');
    }
    $scope.goToNewHouse = function () {
        $state.go('NewOrEditHouse');
    }
    $scope.goToHouse = function () {
        $state.go('House');
    }

}]);
