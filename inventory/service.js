"use strict";

const tracer = require("./tracer")("inventory-service");
const api = require("@opentelemetry/api");
const express = require("express");
const axios = require("axios").default;

const app = express();
const PORT = process.env.PORT || 3000;

const getController = () => {
  const router = express.Router();
  const resources = [];
  router.get("/", (req, res) => res.send(resources));
  router.post("/", (req, res) => {
    resources.push(req.body);
    return res.status(201).send(req.body);
  });
  return router;
};

const login = (req, res, next) => {
  const { authorization } = req.headers;
  if (authorization && authorization.includes("secret_token")) {
    next();
  } else {
    res.sendStatus(401);
  }
};

app.use(express.json());
app.get("/healthz", (req, res) => res.status(200).send("OK"));

app.get("/stock/deduct/:itemId", async (req, res) => {
  // Custom span
  const currentSpan = api.trace.getSpan(api.context.active());
  console.log(`traceid: ${currentSpan.spanContext().traceId}`);

  const span = tracer.startSpan("Call /deduct", {
    kind: 1, // server
    attributes: { key: "value" },
  });
  // Annotate our span to capture metadata about the operation
  span.addEvent("invoking /deduct");

  const result = await axios.post(
    `http://localhost:${PORT}/process`,
    {
      price: 1000
    },
    {
      headers: {
        Authorization: "secret_token",
      },
    }
  );
  span.end();
  return res.status(201).send(result.data);
});
app.use("/process", login, getController());

app.listen(PORT, () => {
  console.log(`Listening on http://localhost:${PORT}`);
});