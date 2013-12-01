/**
 * The dialog that is displayed to log in to the system
 */
define([
    "dojo/_base/declare",
    "dojo/i18n",
    "dojo/dom-construct",
    "dojo/query",
    "dijit/Dialog",
    "books/core/request",
    "dojo/text!./templates/login-dialog.tmpl",
    "dojo/i18n!./nls/login-dialog"
], function (declare, i18n, domConstruct, query, Dialog, Request, template) {
    return declare([], {
        /**
         * Constructor that will actually build the dialog
         */
        constructor: function () {
            this.messages = i18n.getLocalization("books.application.user-area", "login-dialog", this.lang);
            this._dialog = new Dialog({
                title: this.messages.title
            });
        },

        /**
         * Actually cause the dialog to get displayed
         * @method show
         * @public
         */
        show: function () {
            var req = new Request("api/auth/remote/providers");
            req.on("success", dojo.hitch(this, function(providers) {
                var dialogContent = domConstruct.toDom(template),
                    providerList = query(".socialButtons", dialogContent);

                providers.forEach(function (provider) {
                    var providerNode = domConstruct.create("li", {
                        "class": provider.id,
                        "innerHTML": provider.label
                    });
                    domConstruct.place(providerNode, providerList[0], "last");
                });

                this._dialog.set("content", dialogContent);
                this._dialog.show();
            }));
            req.go();
        }
    });
});


