'use strict'

const express = require('express')
const { ExpressPrometheusMiddleware } = require('@matteodisabatino/express-prometheus-middleware')


const app = express()
const epm = new ExpressPrometheusMiddleware()

const port = process.env.PORT || 3002

app.use(epm.handler)

// Main API
app.get('/', (req, res, next) => {
    setTimeout(() => {
        res.json({ message: 'This is service from NodeJS' })
        next()
    }, Math.round(Math.random() * 200))
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`)
})

process.on('SIGTERM', () => {
    clearInterval(metricsInterval)
    process.exit(0)
})