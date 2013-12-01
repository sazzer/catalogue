/**
 * Wrapper around an AJAX Request
 */
define([
    "dojo/_base/declare",
    "dojo/Evented",
    "dojo/request/xhr"], function(declare, Evented, xhr) {
    return declare([Evented], {
        /**
         * Construct the request
         * @param url the URL to call
         * @param method The HTTP Method to use. Defaults to GET
         */
        constructor: function(url, method) {
            this._url = url;
            this._method = method || "GET";
            this._params = {};
        },
        /**
         * Set a single param
         * @param key the key of the param
         * @param value the value of the param
         */
        setParam: function(key, value) {
            this._params[key] = value;
        },
        setParams: function(params) {
            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    this.setParam(key, params[key]);
                }
            }
        },
        go: function() {
            var self = this;
            xhr(this._url, {
                handleAs: "json",
                method: this._method,
                data: this._params
            }).then(function(data) {
                    // On Success
                    self.emit("success", data);
                }, function(err) {
                    // On Error
                    self.emit("error", err);
                }, function(evt) {
                    // Progress Event, if supported
                    self.emit("progress", evt);
                });
        }
    });
});
