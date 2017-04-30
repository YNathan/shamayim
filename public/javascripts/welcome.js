app.controller('welcome', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', '$rootScope', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions, $rootScope) {

    ShamayimFunctions.setIsLoggedCookie("false");
    // Language Section

    $rootScope.Languages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            HouseLanguage: 'default'
        }
    };

    $rootScope.Languages = ShamayimFunctions.getExistingLanguages();

    function getLanguage(szLanguageName) {
        // Get information conserning the house
        $http.get("/GET_LANGUAGE/" + szLanguageName)
            .then(function successCallback(response) {
                    $rootScope.dictionary = response.data;
                    $rootScope.pageDirection = $rootScope.dictionary.Dictionary[0].PageDirection;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        ShamayimFunctions.setLanguageCookie(szLanguageName);

    }


    getLanguage("עברית");

    $rootScope.$watch('Languages.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            HouseLanguageName = newVal;
            getLanguage(newVal);

        }
    })

    // End Of Language Section


    $rootScope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $rootScope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $rootScope.goToHouses = function () {
        $state.go('Houses');
    }
    $rootScope.goToNewHouse = function () {
        $state.go('NewOrEditHouse');
    }
    $rootScope.goToSystemManager = function () {
        $state.go('Manager');
    }
    $rootScope.goToHouse = function () {
        $state.go('House');
    }
    $rootScope.goToHouse = function () {
        $state.go('House');
    }
    $rootScope.logout = function () {
        ShamayimFunctions.setIsLoggedCookie("false");
        $state.go('welcome');

    }
    $rootScope.showLrButton = function () {
        if (ShamayimFunctions.getIsLoggedCookie() == "true") {
            return false
        } else {
            return true;
        }
    }


}]).directive('lazyLoad', function ($timeout) {
    return {
        restrict: 'A',
        scope: {},
        link: function (scope, elem, attrs) {
            $timeout(function () {
                elem.attr('src', attrs.llSrc)
            });
        },
    }
});
