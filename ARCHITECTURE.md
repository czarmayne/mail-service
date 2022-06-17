
[[_TOC_]]

## Requirements

[Technical Assessment](https://docs.google.com/document)

## Value Proposition

- One template for all your mail providers
- Performance
    - Async call and retry capability
- Support for more mail providers

## Overview

Solutions aims to provide users ease of use without having to worry about the different templates of support mail providers

## Logical Architecture

_see /docs/design.drawio_
[![logical-diagram](link to image on GH)](link to your URL)
## Use Case

_see /docs/design.puml_
[![sequence-diagram](link to image on GH)](link to your URL)

## Components
_Question: What is the current app lifecycle strategy (change management), patching?_

| Application | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Web App` | `WEB` | Web interface accessible by users |
| `API Gateway` | `PROXY` | NGINX that routes request made by web app |
| `SendGrid` | `WEB` | Mail Provider |
| `MailGun` | `WEB` | Mail Provider |

Optional:
| Application | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `ELK` | `Log Management` | Platform (ElasticSearch - logging, LogStash - storage & forwarding, and Kibana - visualization) |
| `APM` | `Metrics` | Java agent that can be integrated in ELK for full traceability |
| `S3` | `Storage` | For any attachment in the future |
| `Kafka` | `Queue` | For backup in cases where all providers are down (slim chance, but just need to add it here) |
| `Azure KeyVault/Vault Hashicorp` | `Secrets Management` | Will be used for keeping the API Keys |

## Security
### Data at Rest & Data in Use
| Aspect | Item     | Description | Strategy |
| :-------- | :------- | :------------------------- | :------ |
| Data at Rest| X | No requirements to store it | X |
| Data at Use | Mail Service | Endpoint Security | API Tokens |

### Certificate, Password, & Key Management
_need to decide on technology to be used_

### IAM
_this is more on the web app access; NPAs as well if data will be stored in the future_

## Cost
_add here a comparison for the different 3rd party mail vendors_
_why? to decide on who will be the primary and how many times should the service retry before the failover_

## Availability Model
| System | SLA     | Latency Performance Level | Strategy in Event of Service Loss | Strategy in Event of Site Loss| Replication Strategy|
| :-------- | :------- | :------------------------- | :------ | :------ | :------ |

_to be defined per system; also, consider the operational resilience: recoverability_
- do we have any audit compliance for the max outage or any SLO and SLA with the clients for consideration here?


