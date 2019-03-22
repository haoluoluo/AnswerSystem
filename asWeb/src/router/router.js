import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "index",
      component: () => import("@/views/index"),
      redirect: "/home",
      children: [
        {
          path: "home",
          name: "home",
          component: () => import("@/views/user/home")
        },
        {
          path: "examList",
          name: "examList",
          component: () => import("@/views/user/exam/examList")
        },
        {
          path: "userRecord",
          name: "userRecord",
          component: () => import("@/views/user/record/userRecord")
        },
        {
          path: "settings",
          name: "settings",
          component: () => import("@/views/user/settings/settings")
        },
        {
          path: "userSetting",
          name: "userSetting",
          component: () => import("@/views/user/settings/userSettings")
        }
      ]
    },

    {
      path: "/ilogin",
      name: "ilogin",
      component: () => import("@/views/user/iLogin"),
      redirect: "/ilogin/login",
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/views/user/login/login")
        },
        {
          path: "register",
          name: "register",
          component: () => import("@/views/user/login/register")
        },
        // {
        //   path: "regCallback",
        //   name: "regCallback",
        //   component: regCallback
        // },
        {
          path: "forgotpassword",
          name: "forgotpassword",
          component: () => import("@/views/user/login/forgotPassword")
        },
        {
          path: "resetpassword",
          name: "resetpassword",
          component: () => import("@/views/user/login/resetPassword")
        }
      ]
    },

    // {
    //   path: "/question/:id",
    //   name: "question",
    //   // meta: {
    //   //   requireAuth: true, // 判断是否需要登录
    //   // },
    //   component: () => import("@/views/user/question/question")
    // },

    {
      path: "/beforeAns",
      name: "beforeAns",
      meta: {
        requireAuth: true, // 判断是否需要登录
      },
      component: () => import("@/views/user/question/beforeAns")
    },
    // {
    //   path: "/head",
    //   name: "head",
    //   component: () => import("@/views/head")
    // },
    // {
    //   path: "/examResult",
    //   name: "examResult",
    //   component: () => import("@/views/user/question/examResult")
    // },
    // {
    //   path: "/resultAll",
    //   name: "resultAll",
    //   component: () => import("@/views/user/question/resultAll")
    // },
  {
      path: "/examCard/:examId/:questionId",
      name: "examCard",
      meta: {
        requireAuth: true, // 判断是否需要登录
      },
      component: () => import("@/views/user/question/examCard")
    }
  ]
});
