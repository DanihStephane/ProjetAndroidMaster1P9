'use strict';

const { dbconnect } = require('../services/DBConnection.service');
const TokenHelper = require('../services/TokenHelper');

module.exports = class UtilisateurModel {



  async insert (authorization, user) {
    let con = null;

    try {
      con = dbconnect();
      con.connect();
      
      /** **/
      throw new Error('Not supported yet');

    } catch (err) {
      if (con !== null) {
        await con.query('ROLLBACK');
      }
      if (err.code === '23505') {
        throw new Error("Le pseudo '" + user.pseudo + "' est déjà utiliser par un " +
                    'autre utilisateur. Veuillez en choisir un autre'
        );
      }
      throw err;
    } finally {
      if (con !== null) con.end();
    }
  }

  async update (authorization, userInformation) {
    let con = null;
    const tokenHelper = new TokenHelper();
    try {
      con = dbconnect();
      con.connect();
      /** **/
      throw new Error('Not supported yet');

    } catch (err) {
      if (con !== null) {
        await con.query('ROLLBACK');
      }
      throw err;
    } finally {
      if (con !== null) con.end();
    }
  }


  

  async login (pseudo, password) {
    let con = null;
    const tokenHelper = new TokenHelper();
    try {
      con = dbconnect();
      con.connect();
      await con.query('BEGIN');

      /***  A implementer ***/

      throw new Error('Not supported Yet');

      /*** ***/

    } catch (err) {
      if (con !== null) {
        await con.query('ROLLBACK');
      }
      throw err;
    } finally {
      if (con !== null) con.end();
    }
  }

  async logout (authorization) {
    let con = null;
    const tokenHelper = new TokenHelper();
    const token = tokenHelper.getTokenFromBearerToken(authorization);
    try {
      con = dbconnect();
      con.connect();
      await con.query('BEGIN');
      await tokenHelper.deleteToken(con, token);
      await con.query('COMMIT');
      return {
        message: 'Vous vous êtes déconnectez avec succès'
      };
    } catch (err) {
      if (con !== null) {
        await con.query('ROLLBACK');
      }
      throw err;
    } finally {
      if (con !== null) con.end();
    }
  }


};
