/**
 * The dialog that is displayed to log in to the system
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dojo/dom-construct",
  "dojo/query",
  "dijit/Dialog",
  "dojo/text!./templates/login-dialog.tmpl",
  "dojo/i18n!./nls/login-dialog"
  ], function(declare, i18n, domConstruct, query, Dialog, template) {
  return declare([], {
    /**
     * Constructor that will actually build the dialog
     */
    constructor: function() {
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
    show: function() {
      var dialogContent = domConstruct.toDom(template),
          providerList = query(".socialButtons", dialogContent),
          providers = [{
              id: "facebook",
              label: "Sign in with Facebook"
          }, {
              id: "google",
              label: "Sign in with Google"
          }, {
              id: "twitter",
              label: "Sign in with Twitter"
          }, ];

      providers.forEach(function(provider) {
          var providerNode = domConstruct.create("li", {
              "class": provider.id,
              "innerHTML": provider.label
          });
          domConstruct.place(providerNode, providerList[0], "last");
      });

      this._dialog.set("content", dialogContent);
      this._dialog.show();
    }
  });
});


