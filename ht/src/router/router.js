import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/admin'
    },
    // admin
    {
      path: '/admin/login',
      name: 'alogin',
      component: () => import('@/views/admin/login/login')
    },
    {
      path: '/admin',
      name: 'alogin',
      component: () => import('@/views/admin/index'),
      redirect: '/admin/userMgr',
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: 'userMgr',
          name: 'userMgr',
          component: () => import('@/views/admin/userManager'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'userUpdate',
          name: 'userUpdate',
          component: () => import('@/views/admin/updateUser'),
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'examMgr',
          name: 'examMgr',
          component: () => import('@/views/admin/exam/examManager'),
          meta: {
            requireAuth: true,
          },
        },
        {
          path: 'examadd',
          name: 'examadd',
          component: () => import('@/views/admin/exam/examAdd'),
          meta: {
            requireAuth: true,
          }
        },
        {
          path: 'questionMgr/:id',
          name: 'questionMgr',
          component: () => import('@/views/admin/questionManager'),
          meta: {
            requireAuth: true,
          },
        },
        {
          path: 'questiontype/:examid',
          name: 'questiontype',
          component: () => import('@/views/admin/question/questionType'),
          meta: {
            requireAuth: true,
          }
        },
        {

          path: 'singlequestion',
          name: 'singlequestion',
          component: () => import('@/views/admin/question/singlequestion'),
          meta: {
            requireAuth: true,
          }
        },
        {
          path: 'multiplequestion',
          name: 'multiplequestion',
          component: () => import('@/views/admin/question/multiplequestion'),
          meta: {
            requireAuth: true,
          }
        },
        {
          path: 'truefalsequestion',
          name: 'truefalsequestion',
          component: () => import('@/views/admin/question/truefalsequestion'),
          meta: {
            requireAuth: true,
          }
        },
        {
          path: 'recordsMgr',
          name: 'recordsMgr',
          component: () => import('@/views/admin/recordManager'),
          meta: {
            requireAuth: true,
          }
        }
      ]
    }
  ]
})
