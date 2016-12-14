app.controller('house', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', function ($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions) {

    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.house_id;
    $scope.state;
    $scope.city = "";
    $scope.street = "";
    $scope.house_number;
    $scope.house_kind;
    $scope.number_of_rooms;
    $scope.number_of_living_rooms;
    $scope.number_of_kitchens;
    $scope.number_of_bedrooms;
    $scope.number_of_bathrooms;
    $scope.location_kind;
    $scope.comments = "";
    $scope.purchase_price;
    $scope.treatment_fees;
    $scope.renovation_fees_for_sale;
    $scope.renovation_fees_for_renting;
    $scope.divers_fees;
    $scope.userName = ShamayimFunctions.getCookie("username");
    var houseName = "";
    var tempArr = [];
    $scope.dictionary;
    var results;
    // For the house
    $scope.houses = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            house: 'default'
        }
    };


    // Get information conserning the
    $http.get("/GET_LIST_OF_HOUSES")
        .then(function successCallback(response) {
                angular.forEach(response.data.houses, function (value, key) {
                    itemName = {
                        id: key,
                        house: value
                    }
                    tempArr.push(itemName);
                    $scope.houses.availableOptions.push(itemName.house);
                }, $scope.houses);
            },
            function error(response) {
                ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
            });

    function checkIfNeedConfirming() {}
    $interval(checkIfNeedConfirming, 200000);

    // For Images
    $scope.housePathesImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            imagesSource: 'default'
        }
    };

    $scope.houseImages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            image: 'default'
        }
    };

    $scope.getImages = function(szHouseImagesPath) {
        var fileToReturn;
        $http.get('/GET_SPECIFIC_FILE/' + szHouseImagesPath)
            .then(function successCallback(response,file) {
                    fileToReturn = file;
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });
        return fileToReturn;
    }

    function getHouseImages(nHouseId) {
        $http.get('/GET_FILES_PATHS/' + nHouseId)
            .then(function successCallback(response) {
                    angular.forEach(response.data.files, function (value, key) {
                        itemName = {
                            id: key,
                            imagesSource: value
                        }
                        $scope.housePathesImages.availableOptions.push(itemName.imagesSource);
                    }, $scope.housePathesImages);
            
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }

    // Logic methods section
    function getHouse(nHouseId) {
        // Get information conserning the house
        $http.get("/GET_HOUSE_BY_ID/" + nHouseId)
            .then(function successCallback(response) {
                    $scope.house_id = response.data.house.house_id;
                    $scope.state = response.data.house.state;
                    $scope.city = response.data.house.city;
                    $scope.street = response.data.house.street;
                    $scope.house_number = response.data.house.house_number;
                    $scope.house_kind = response.data.house.house_kind;
                    $scope.number_of_rooms = response.data.house.number_of_rooms;
                    $scope.number_of_living_rooms = response.data.house.number_of_living_rooms;
                    $scope.number_of_kitchens = response.data.house.number_of_kitchens;
                    $scope.number_of_bedrooms = response.data.house.number_of_bedrooms;
                    $scope.number_of_bathrooms = response.data.house.number_of_bathrooms;
                    $scope.location_kind = response.data.house.location_kind;
                    $scope.comments = response.data.house.comments;
                    $scope.purchase_price = response.data.house.purchase_price;
                    $scope.treatment_fees = response.data.house.treatment_fees;
                    $scope.renovation_fees_for_sale = response.data.house.renovation_fees_for_sale;
                    $scope.renovation_fees_for_renting = response.data.house.renovation_fees_for_renting;
                    $scope.divers_fees = response.data.house.divers_fees;
                    newMapLocation($scope.house_number, $scope.street, $scope.city, $scope.state);
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load houses");
                });

        // Get images
        $scope.houseImages.availableOptions = [];
        $scope.housePathesImages.availableOptions = [];
        getHouseImages(nHouseId);

    }




    // Language Section
    // For the Language
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
        if (ShamayimFunctions.getLanguage(szLanguageName) == true) {
            $scope.dictionary = ShamayimFunctions.getDictionary();
        }
        $scope.dictionary = ShamayimFunctions.getDictionary();

    }
    getLanguage("English");
    $scope.$watch('Languages.selectedOption', function (newVal, oldVal) {
            if (newVal != oldVal) {
                HouseLanguageName = newVal;
                getLanguage(newVal);

            }
        })
        // End Of Language Section

    // Just check if there is a user name
    if (ShamayimFunctions.getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }
    getLanguage("English");
    $scope.$watch('houses.selectedOption', function (newVal, oldVal) {
        if (newVal != oldVal) {
            houseName = newVal;
            getHouse(newVal.house_id);

        }
    })







    function newMapLocation(nNumberOfHouse, szStreetName, szCityName, szStateName) {
        var longitude = 0.0;
        var latitude = 0.0;

        // Get information conserning the house
        $http.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + nNumberOfHouse + "," + szStreetName + "," + szCityName + "," + szStateName + "&key=AIzaSyCOx3ErCzTt9zayUQluJsKo_Gpnp4ztMbI")
            .then(function successCallback(response) {
                    latitude = response.data.results[0].geometry.location.lat;
                    longitude = response.data.results[0].geometry.location.lng;
                    var uluru = {
                        lat: latitude,
                        lng: longitude
                    };
                    var map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 15,
                        center: uluru
                    });
                    var marker = new google.maps.Marker({
                        position: uluru,
                        map: map
                    });
                },
                function error(response) {
                    ShamayimFunctions.showAlert("Your attention please", response.data, "cant load maps");
                });

    }
    $scope.imageSrc = "images/background.jpg";
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
