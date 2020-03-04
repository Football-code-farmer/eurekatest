import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Authorization:''
  },
  mutations: {
    changeLogin(state, userToken){
      state.Authorization = userToken;
    }
  },
});
