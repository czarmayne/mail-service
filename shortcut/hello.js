const axios = require('axios')
const github = require('@actions/github')
const core = require('@actions/core')

async function sayHello(){
    const S_TOKEN = process.argv[2] // TODO: Maybe we can access process inside other functions as well?
    const G_TOKEN = process.argv[3]
    const PR_NUMBER = process.argv[4]
    const PR_STATE = process.argv[5]
    const USER = process.argv[6]

    console.log('HELLO WORLD!');
    console.log(`Get the values from pipe: \n ${S_TOKEN} = ${G_TOKEN} = ${PR_NUMBER} = ${PR_STATE} = ${USER}`);
}
// Main call
(async () => {
    await sayHello()
})()