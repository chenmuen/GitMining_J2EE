/**
 * Created by chenm on 2016/6/7.
 */
var SingleLanguageDAO = require("../dao/SingleLanguageDAO");
var LanguageTagDAO = require("../dao/LanguageTagDAO");
var Async = require('async');
var catagory = [
    {
        name: "Object-oriented",
        languages: [
            'Lisp',
            'Common Lisp',
            'Dylan',
            'SmallTalk',
            'Self',
            'JavaScript',
            'Python',
            'Ruby',
            'Lua',
            'Perl',
            'Objective-C',
            'Java',
            'C++',
            'CLU',
            'Simula',
            'Scala',
            'Oberon',
            'Modula-3',
            'Delphi',
            'Eiffel',
            'C#',
            'Visual Basic',
            'Fortran',
            'Speedcoding',
            'COBOL',
            'PHP',
            'Tcl'
        ]
    },
    {
        name: "Functional",
        languages: [
            'Agda',
            'Charity',
            'Clean',
            'Coq',
            'Curry',
            'Elm',
            'Frege',
            'Haskell',
            'Hope',
            'Joy',
            'Mercury',
            'Miranda',
            'Idris',
            'SequenceL',
            'APL',
            'ATS',
            'CAL',
            'C++',
            'C#',
            'Ceylon',
            'D',
            'Dart',
            'Curl',
            'ECMAScript',
            'ActionScript',
            'JavaScript',
            'JScript',
            'Erlang',
            'F#',
            'FPr',
            'Groovy',
            'Hop',
            'J',
            'Java',
            'Julia',
            'Lisp',
            'Clojure',
            'Common Lisp',
            'Dylan',
            'Emacs Lisp',
            'LFE',
            'Little b',
            'Logo',
            'Scheme',
            'Racket',
            'Tea',
            'Mathematica',
            'ML',
            'Nemerle',
            'Opal',
            'OPS5',
            'Poplog',
            'Python',
            'Q',
            'R',
            'Ruby',
            'REFAL',
            'Rust',
            'Scala',
            'Spreadsheets',
            'Wolfram Language'
        ]
    },
    {
        name: "Imperative",
        languages: [
            'Ada',
            'ALGOL',
            'BASIC',
            'Blue',
            'C',
            'C++',
            'C#',
            'Ceylon',
            'COBOL',
            'D',
            'eC',
            'FORTRAN',
            'GAUSS',
            'Go',
            'Groovy',
            'Java',
            'Julia',
            'Lua',
            'MATLAB',
            'Machine language',
            'Modula-2, Modula-3',
            'MUMPS',
            'Nim',
            'Oberon',
            'Object Pascal',
            'OCaml',
            'Pascal',
            'Perl',
            'PHP',
            'PROSE',
            'Python',
            'Ruby',
            'Rust',
            'Wolfram Language'
        ]
    },
    {
        name: "Procedural",
        languages: [
            'JavaScript',
            'Python',
            'Lua',
            'Lisp',
            'Logo',
            'UCBLogo',
            'Common Lisp',
            'Scheme',
            'Forth',
            'Tcl',
            'AWK',
            'Python',
            'Lua',
            'CLU',
            'C++',
            'D',
            'PHP',
            "Perl",
            'Modula-2',
            'Modula-3',
            'ALGOL',
            'ALGOL 60',
            'C',
            'BCPL',
            'Fortran',
            'PL/I',
            'BASIC',
            'COBOL',
            'MATLAB',
            'LPC',
            'MUF',
            'Oaklisp',
            'Scala',
            'ML',
            "Tcl"

        ]
    },
    {
        name: "Interactive mode",
        languages: [
            'BASIC',
            'Clojure',
            'Common Lisp',
            'Darts',
            'Erlang',
            'FSharp',
            'Forth',
            'FPr',
            'Fril',
            'GAUSS',
            'Groovy',
            'Haskell',
            'IDL',
            'J',
            'Julia',
            'MUMPS',
            'Maple',
            'Mathematica',
            'MATLAB',
            'Pike',
            'PostScript',
            'PROSE',
            'R',
            'REXX',
            'Scheme',
            'Smalltalk',
            'S-Lang',
            'Windows PowerShell',
            'Visual FoxPro'
        ]
    },
    {
        name: "Scripting",
        languages: [
            'AppleScript',
            'AWK',
            'BeanShell',
            'Bash',
            'Ch',
            'CLIST',
            'ColdFusion',
            'ECMAScript',
            'ActionScript',
            'JavaScript',
            'JScript',
            'CMS EXEC',
            'EXEC 2',
            'F-Script',
            'Falcon',
            'Game Maker Language',
            'ICI',
            'Io',
            'JASS',
            'Groovy',
            'Join Java',
            'Julia',
            'Lasso',
            'Lua',
            'MAXScript',
            'MEL',
            'Oriel',
            'Perl',
            'PHP',
            'Pikt',
            'Python',
            'R',
            'REBOL',
            'REXX',
            'Revolution',
            'Ruby',
            'Smalltalk',
            'S-Lang',
            'sed',
            'Tea',
            'Tcl',
            'TorqueScript',
            'VBScript',
            'WebDNA',
            'Windows PowerShell',
            'Winbatch'
        ]
    },
    {
        name: 'Concurrent',
        languages: [
            'Ada',
            'Alef',
            'Ateji PX',
            'ChucK',
            'Cilk',
            'Cω',
            'Clojure',
            'Chapel',
            'Fortran',
            'Concurrent Pascal',
            'Curry',
            'E',
            'Eiffel',
            'Erlang',
            'Go',
            'Java',
            'Join Java',
            'X10',
            'Julia',
            'Join-calculus',
            'Joule',
            'Limbo',
            'MultiLisp',
            'occam',
            'occam-π',
            'Orc',
            'Oz',
            'Pict',
            'Rust',
            'SALSA',
            'Scala',
            'SequenceL',
            'SR',
            'Unified Parallel C',
            'XProc',
        ]
    }
];

var application = [
    {
        name: 'Mobile Application',
        languages: [
            'Objective-C',
            'Swift',
            'Java',
            'C#'
        ]
    },
    {
        name: 'Website',
        languages: [
            'HTML',
            'CSS',
            'JavaScript',
            'Java',
            'PHP',
            'C#',
            'CoffeeScript',
            'Python',
            'JSP'
        ]
    },
    {
        name: 'Web Application',
        languages: [
            'HTML',
            'CSS',
            'JavaScript',
            'Java',
            'PHP',
            'C#',
            'Python'
        ]
    },
    {
        name: 'Server',
        languages: [
            'JavaScript',
            'Java',
            'PHP',
            'C',
            'C++',
            'Python',
            'Erlang',
            'Ruby',
            'C#',
            'ColdFusion',
            'AIR',
            'CGI',
            'Delphi'
        ]
    },
    {
        name: 'Game',
        languages: [
            'Python',
            'Lua',
            'C#',
            'C++',
            'Java',
            'Objective-C'
        ]
    },
    {
        name: 'Science',
        languages: [
            'MATLAB',
            'Perl',
            'Lisp',
            'Prolog',
            'R',
            'Fortran',
            'BrainFuck',
            'Julia'
        ]
    }
];

function addTag(isApp) {
    SingleLanguageDAO.findAll('language', 1, 10000, function (err, languages) {
        var array = isApp?application:catagory;
        
        languages.forEach(function (language) {
            var lang = language.language;

            var resultList = [];

            Async.forEach(array, function (ca, cb) {
                if(contains(ca.languages, lang)) {
                    var obj = {language: lang, tag_name: ca.name, is_app:isApp?1:0};
                    resultList.push(obj);
                }
                cb();
            }, function (err) {
                console.log(lang + " " + resultList.length);
                resultList.forEach(function (tag) {
                    LanguageTagDAO.add(tag, function (err) {
                        if(err) {
                            console.log(err);
                        }else {
                            console.log(lang + " success!!!")
                        }
                    })
                })
            })
        })
    })
}

function contains(arr, obj) {
    var i = arr.length;
    while(i--) {
        if(arr[i].toLowerCase() == obj.toLowerCase()) {
            return true;
        }
    }
    return false;
}

addTag(true);