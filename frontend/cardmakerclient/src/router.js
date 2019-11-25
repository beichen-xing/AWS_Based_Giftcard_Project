import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  routes: [{
      path: "/",
      name: "home",
      component: () => import( /* webpackChunkName: "about" */ "./views/Home")
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import( /* webpackChunkName: "about" */ "./views/About")
    },
    {
      path: "/editor",
      name: "Editor",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import( /* webpackChunkName: "Editor" */ "./views/Editor")
    },
    {
      path: "/preview",
      name: "CardPreview",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import( /* webpackChunkName: "Editor" */ "./views/CardPreview")
    },
    {
      path: "/gallery",
      name: "Gallery",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import( /* webpackChunkName: "Gallery" */ "./views/Gallery")
    }
  ]
});