'use strict';

const md5 = require('md5');
const { expirationToken } = require('../../config/app_config/token');
const DateHelper = require('../services/DateHelper');

module.exports = class TokenHelper {
  constructor () {
    this.expirationToken = expirationToken;
    this.errorMessage = {
      utilisateur_introuvable: 'Le token ne correspond à aucun utilisateur',
      auth_different_bearer: "Le token envoyé n'est pas de type Bearer"
    };
  }

  getTokenFromBearerToken (authorization) {
    if (!authorization) {
      throw new Error('Accès non autorisé, veuillez vous connecter');
    }
    const tokenSplited = authorization.split(' ');
    if (tokenSplited[0] !== 'Bearer' || tokenSplited.length !== 2) {
      throw new Error(this.errorMessage.auth_different_bearer);
    }
    return tokenSplited[1];
  }

  async insertToken (con, user) {
   /** **/
   throw new Error('Not supported yet');
   
  }

  async deleteToken (con, token) {
    /*** ****/
    throw new Error('Not supported yet');
  }

  async getUserByToken (con, token) {
    /** Not Supported Yet **/
    throw new Error('Not Supported Yet');
  }

  static generateToken (id) {
    const date = new Date();
    const token = md5(date + id + date);
    return token;
  }
};
