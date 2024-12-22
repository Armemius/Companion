import { writable } from "svelte/store";

export type Authorization = {
  authorized: boolean;
  balance: number;
  token: string;
  user: {
    login: string;
    username: string;
  };
};

const AUTH_STORE_ID = "authStore";

export const authStatusStore = writable<Authorization | null>(null);

const storedAuthStatus = localStorage.getItem(AUTH_STORE_ID);
if (storedAuthStatus) {
  authStatusStore.set(JSON.parse(atob(storedAuthStatus)));
}

authStatusStore.subscribe((value) => {
  localStorage.setItem(AUTH_STORE_ID, btoa(JSON.stringify(value)));
});
