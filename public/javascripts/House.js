app.controller('house', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', 'ShamayimFunctions', function($scope, $http, $state, $interval, $mdDialog, $mdSidenav, ShamayimFunctions) {

    // Just print kind of 'hay message'
    $scope.message = 'M. ' + ShamayimFunctions.getCookie("username");
    $scope.house_id = "1";
    $scope.state = "ca";
    $scope.city = "la";
    $scope.street = "bafla";
    $scope.house_number = 32;
    $scope.house_kind = 3;
    $scope.number_of_rooms = 1;
    $scope.number_of_living_rooms = 1;
    $scope.number_of_kitchens = 1;
    $scope.number_of_bedrooms = 1;
    $scope.number_of_bathrooms = 1;
    $scope.location_kind = 5;
    $scope.comments = "אחלה של בית";
    $scope.purchase_price = 0.0;
    $scope.treatment_fees = 21.2;
    $scope.renovation_fees_for_sale = 54.5;
    $scope.renovation_fees_for_renting = 24.5;
    $scope.divers_fees = 54.2;
    $scope.userName = ShamayimFunctions.getCookie("username");
    var houseName = "";
    var tempArr = [];
    $scope.dictionary = {
        "Dictionary": [{
            "Dictionary": "English",
            "HouseId": "Number Of House That Recording In The System",
            "Address": "Address",
            "State": "State",
            "City": "City",
            "Street": "Street",
            "HouseNumber": "House Number",
            "HouseKind": "Kind Of House",
            "NumberOfRooms": "Number Of Rooms",
            "NumberOfLivingRooms": "Number Of Living Rooms",
            "NumberOfKitchens": "Number Of Kitchens",
            "NumberOfBedrooms": "Number Of Bedrooms",
            "NumberOfBathrooms": "Number Of Bathrooms",
            "LocationKind": "LocationKind",
            "Score": "Score",
            "Comments": "Comments",
            "PurchasePrice": "Purchase Price",
            "TreatmentFees": "Treatment Fees",
            "RenovationFeesForSale": "Renovation Fees For Sale",
            "RenovationFeesForRenting": "Renovation Fees For Renting",
            "GeneralHouseDetailes": "General House Details",
            "FinancialHouseDetailes": "Financial House Details",
            "DiversFees": "Divers Fees"
        }]
    };


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
                angular.forEach(response.data.houses, function(value, key) {
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

    function checkIfNeedConfirming() {
        /*// Get information conserning the houses
        $http.get("/GET_LIST_OF_HOUSES")
            .then(function successCallback(response) {
                    angular.forEach(response.data.houses, function(value, key) {
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
   */
    }
    $interval(checkIfNeedConfirming, 200000);


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
       ShamayimFunctions.getLanguage(szLanguageName);
       ShamayimFunctions.getLanguage(szLanguageName);
       $scope.dictionary = ShamayimFunctions.dictionary;
    }
    $scope.$watch('Languages.selectedOption', function(newVal, oldVal) {
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

    $scope.$watch('houses.selectedOption', function(newVal, oldVal) {
        if (newVal != oldVal) {
            houseName = newVal;
            getHouse(newVal.house_id);

        }
    })




    var results = {
        "results": [{
            "address_components": [{
                "long_name": "283",
                "short_name": "283",
                "types": ["street_number"]
            }, {
                "long_name": "Prince Street",
                "short_name": "Prince St",
                "types": ["route"]
            }, {
                "long_name": "Springfield/Belmont",
                "short_name": "Springfield/Belmont",
                "types": ["neighborhood", "political"]
            }, {
                "long_name": "Newark",
                "short_name": "Newark",
                "types": ["locality", "political"]
            }, {
                "long_name": "Essex County",
                "short_name": "Essex County",
                "types": ["administrative_area_level_2", "political"]
            }, {
                "long_name": "New Jersey",
                "short_name": "NJ",
                "types": ["administrative_area_level_1", "political"]
            }, {
                "long_name": "États-Unis",
                "short_name": "US",
                "types": ["country", "political"]
            }, {
                "long_name": "07108",
                "short_name": "07108",
                "types": ["postal_code"]
            }, {
                "long_name": "2629",
                "short_name": "2629",
                "types": ["postal_code_suffix"]
            }],
            "formatted_address": "283 Prince St, Newark, NJ 07108, États-Unis",
            "geometry": {
                "location": {
                    "lat": 40.7269049,
                    "lng": -74.1896381
                },
                "location_type": "ROOFTOP",
                "viewport": {
                    "northeast": {
                        "lat": 40.7282538802915,
                        "lng": -74.18828911970849
                    },
                    "southwest": {
                        "lat": 40.7255559197085,
                        "lng": -74.19098708029151
                    }
                }
            },
            "partial_match": true,
            "place_id": "ChIJCcsncmtTwokR_9zF-itQG94",
            "types": ["street_address"]
        }],
        "status": "OK"
    }


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

    $scope.toggleLeft = function() {
        $mdSidenav('left').toggle();
    }
    $scope.goToCopyright = function() {
        $state.go('Copyright');
    }
    $scope.goToUserInformation = function() {
        $state.go('userInformation');
    }
    $scope.goToHouses = function() {
        $state.go('Houses');
    }
    $scope.goToNewHouse = function() {
        $state.go('NewOrEditHouse');
    }
    $scope.goToHouse = function() {
        $state.go('House');
    }


}]);