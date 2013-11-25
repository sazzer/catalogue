/**
 * The dialog that is displayed to log in to the system
 */
define([
  "dojo/_base/declare",
  "dojo/i18n",
  "dijit/Dialog",
  "dojo/text!./templates/login-dialog.tmpl",
  "dojo/i18n!./nls/login-dialog"
  ], function(declare, i18n, Dialog, template) {
  return declare([], {
    /**
     * Constructor that will actually build the dialog
     */
    constructor: function() {
      this.messages = i18n.getLocalization("books.application.user-area", "login-dialog", this.lang);
      this._dialog = new Dialog({
        title: this.messages.title,
        content: template
      });
    },

    /**
     * Actually cause the dialog to get displayed
     * @method show
     * @public
     */
    show: function() {
      this._dialog.show();
    }
  });
});


