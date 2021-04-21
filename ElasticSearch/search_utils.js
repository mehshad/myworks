$(document).ready(function() {
	/**
	 * Creates a Range field - {range : {'fieldname':{gt:'gt',lt:'lt',gte:'gte',lte:'lte'}}}
	 */
    function Range(fieldName, gt, lt, gte, lte) {
        this["range"] = new RangeChild(fieldName, gt, lt, gte, lte);
    }

    /**
     * Creates a Range field - {'fieldname':{gt:'gt',lt:'lt',gte:'gte',lte:'lte'}}
     */
    function RangeChild(fieldName, gt, lt, gte, lte) {
        this[fieldName] = new RangeBetween(gt, lt, gte, lte);
    }

    /**
     * Creates a Range between - {gt:'gt',lt:'lt',gte:'gte',lte:'lte'}
     */
    function RangeBetween(gt, lt, gte, lte) {
        this.gt = gt;
        this.lt = lt;
        this.gte = gte;
        this.lte = lte;
    }

    /**
     * Creates a simple match object {"match":{'fieldName':'value'}}
     */
    function SimpleMatch(fieldName, value) {
        this["match"] = new SimpleMatchChild(fieldName, value);
    }

    /**
     * Creates a simple key /value field - {'fieldName':'value'}
     */
    function SimpleMatchChild(fieldName, value) {
        this[fieldName] = value;
    }

    /**
     * Creates a wildcard {"wildcard":{'fieldname':{value:'value'}}}
     */
    function WildCardMatch(fieldname, value) {
        this["wildcard"] = new WildCardMatchChild(fieldname, value);
    }

    /**
     * Creates a wildcard match field - {'fieldname':{value:'value'}}
     */
    function WildCardMatchChild(fieldname, value) {
        this[fieldname] = new WildCardValue(value);
    }

    /**
     * Creates a simple value field - {value:'value'}
     */
    function WildCardValue(value) {
        this.value = value;
    }

    /**
     * Creates a  match phrase object {"match_phrase":{'fieldName':'value'}}
     */
    function ArrayMatch(fieldName, value) {
        this["match_phrase"] = new SimpleMatchChild(fieldName, value);
    }

    /**
     * Creates a must with a field object - {"must":[<fields>]}
     * fields should be an array of objects
     */
    function Must(fields) {
        this["must"] = fields;
    }

    /**
     * Creates a should with a field object - {"should":[<fields>]}
     * fields should be an array of objects
     */
    function Should(fields) {
        this["should"] = fields;
    }

    /**
     * Creates a must_not with a field object - {"must_not":[<fields>]}
     * fields should be an array of objects
     */
    function MustNot(fields) {
        this["must_not"] = fields;
    }

    /**
     * Creates a has_child object - {"has_child":{"type":'childType',"query":'query'}}
     * query is a query object
     */
    function HasChild(childType, query) {
        this["has_child"] = new HasChildSubElement(childType, query);
    }

    /**
     * Creates a haschild query object - {"type":'childType',"query":'query'}
     * query is a query object
     */
    function HasChildSubElement(childType, query) {
        this["type"] = childType;
        this["query"] = query;
    }

    function Bool(must, should) {
        this["bool"] = new BoolSubElement(must, should);
    }

    /**
     * Creates a boolean sub element with must and should {"must":[],"should":[]}
     * must and should objects created using Must(fields) and Should(fields) methods
     */
    function BoolSubElement(must, should) {
        Object.assign(this, must);
        Object.assign(this, should);
    }

    /**
     * Creates a generic Element
     * Returns an {<obj>}
     */
    function GenericSubElement(obj) {
        Object.assign(this, obj);
    }

    /**
     * Creates a Query element with queries and aggregations {"query":{},"aggs":{}}
     * must and should objects created using Must(fields) and Should(fields) methods
     */
    function Query(queries, aggregations) {
        this["query"] = new GenericSubElement(queries);
        this["aggs"] = new GenericSubElement(aggregations);
    }

    /**
     * Creates a generic element - {'elementName': {<obj>}}
     */
    function GenericElement(elementName, obj) {
        this[elementName] = new GenericSubElement(obj);
    }


    SearchObject = {
        fieldDetails: {
            "name": "",
            "type": "",
            "value": "",
            "detailfield": false
        },

        setName: function(name) {
            this.fieldDetails.name = name;
        },

        setType: function(type) {
            this.fieldDetails.type = type;
        },

        setValue: function(value) {
            this.fieldDetails.value = value;
        }
    }

    var createObjects = function() {
        var andArray = [];
        for (i = 0; i < 10; i++) { // for something
            SearchObject.setName("name" + i);
            SearchObject.setType("type" + i);
            SearchObject.setValue("value" + i);
            andArray.push(parseObjects(SearchObject.fieldDetails));
        }
        return andArray;
    }



    function parseObjects(searchObject) {
        if (searchObject.type == 'numeric') {
            return null; // not supported now
        } else if (searchObject.type == 'array') {
            return new ArrayMatch(searchObject.name, searchObject.value);
        } else {
            return new SimpleMatch(searchObject.name, searchObject.value);
        }
    }

    /**
     * Creates an elastic search compliant must object
     */
    function createMust(searchObjects) {
        return new Must(searchObjects);
    }

    /**
     * Unused TODO
     */
    $(".button").on("click", function() {
        var andArray = [];
        var orArray = [];
        var should = null;
        $(".andsDrop .dataElement").each(function(i, obj) {
            var fieldName = obj.getAttribute("name");
            console.log(obj.type);
            if (obj.type == 'select-multiple') {
                $(obj.options).each(function(j, jobj) {
                    if (jobj.selected) {
                        orArray.push(new ArrayMatch(fieldName, jobj.value));
                    }
                });
                should = new Should(orArray);
                var orBool = new Bool(null, should);
                andArray.push(orBool);
            } else if (obj.type == 'text') {
                andArray.push(new ArrayMatch(fieldName, obj.value));
            }
        });
        var bool = new Bool(new Must(andArray), null);
        var agg = JSON.parse(Config.constants.search.aggregations);
        console.log(JSON.stringify(new Query(bool, agg)));
        $.ajax({
            method: "POST",
            url: Config.urls.search + "/patients.idx/_search",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(new Query(bool, agg)),
            global: false,
            success: function(res, status, xhr) {


                $(res.aggregations.races.buckets).each(function(k, v) {
                    labels.push(v.key);
                    series.push(v.doc_count);
                });

                
            },
            error: function(e) {
                console.log(e);
            }
        });
    });

    /**
     * Random color generation for graphs - results display
     */
    var dynamicColors = function() {
        var r = Math.floor(Math.random() * 255);
        var g = Math.floor(Math.random() * 255);
        var b = Math.floor(Math.random() * 255);
        return "rgb(" + r + "," + g + "," + b + ")";
     };

     /**
      * Search button click function
      */
    $("#search_patients").on("click", function(e) {
        e.preventDefault();
        var andArray = [];
        // Traverse each drop zone. Each dropzone has conditions that is considered an AND or OR. All dropzones are combined using an AND. The andArray will be populated after this
        $('[id^=drop_zone]').each(function(i, e) {
            var drop_zone_id = e.id;
            var condition = $("#" + drop_zone_id).find($('input[name^="options_"]:checked')).val();
            //TODO - Include and Exclude conditions
            var include = $("#" + drop_zone_id+' input[type="checkbox"][id^="include_"]').is(':checked');
            var conditionsArray = [];
            var bool;
            // In each drop zone, combine the elements using an AND or OR.
            $("#" + drop_zone_id + " .dataElement").each(function(i, obj) {
                var orArray = [];
                var fieldName = obj.getAttribute("name");
                console.log(obj.type);
                //if multi select, combine to an array and add as a single OR condition. This will be nested to the existing condition
                if (obj.type == 'select-multiple') {
                    $(obj.options).each(function(j, jobj) {
                        if (jobj.selected) {
                            orArray.push(new ArrayMatch(fieldName, jobj.value));
                        }
                    });
                    should = new Should(orArray);
                    var orBool = new Bool(null, should);
                    conditionsArray.push(orBool);
                } else if (obj.type == 'text') { // if text, then push as single condition
                    conditionsArray.push(new ArrayMatch(fieldName, obj.value));
                }
            });

            console.log("Include - Not yet implemented ? "+include);
            bool = condition == "and" ? new Bool(new Must(conditionsArray), null) : new Bool(null, new Should(conditionsArray));
            andArray.push(bool);
        });
        // Aggregations are used to tell Elastic search how to aggregate the results. This is now configured in config.json and not dynamic
        var agg = JSON.parse(Config.constants.search.aggregations);
        //The final query sent to elastic search is a combination of all conditions and aggregate
        var finalQuery = new Query(new Bool(new Must(andArray), null), agg);
        $.ajax({
            method: "POST",
            url: ,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(finalQuery),
            success: function(res, status, xhr) {


            },
            error: function(e) {
                console.log(e);
            }
        });
    });

})
