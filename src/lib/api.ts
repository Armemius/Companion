import axios from "axios";

const PROTOCOL = "http";
const HOST = "localhost";
const PORT = "13228";

export const api = axios.create({
  baseURL: `${PROTOCOL}://${HOST}:${PORT}`,
  headers: {
    "Content-Type": "application/json",
  },
});
