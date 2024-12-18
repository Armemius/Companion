import { writable } from "svelte/store";

export type Authorization = {
  authorized: boolean;
  balance: number;
  token: string;
  user: {
    id: string;
    name: string;
  };
};

export const authStatusStore = writable<Authorization | null>(null);
